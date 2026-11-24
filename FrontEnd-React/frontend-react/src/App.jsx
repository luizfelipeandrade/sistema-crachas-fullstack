import { useState, useEffect, useRef } from "react";
import { BadgePreview } from "./components/BadgePreview";
import {
  UserPlus,
  Save,
  RotateCcw,
  Trash2,
  Pencil,
  MessageCircle,
  Printer,
  CheckCircle2,
  Camera,
  X,
} from "lucide-react";

const API_URL =
  import.meta.env.VITE_API_URL || "http://localhost:8080/api/pessoas";

const estadoInicial = {
  nome: "",
  dtNascimento: "",
  email: "",
  wpp: "",
  genero: 0,
  tipoCadastro: 0,
  foto: "",
};

export default function App() {
  const [form, setForm] = useState(estadoInicial);
  const [editandoId, setEditandoId] = useState(null);
  const [erros, setErros] = useState({});
  const [cadastrados, setCadastrados] = useState([]);
  const [mensagemSucesso, setMensagemSucesso] = useState("");
  const [apiOnline, setApiOnline] = useState(false);
  const [crachaImpressao, setCrachaImpressao] = useState(null);
  const inputFotoRef = useRef(null);

  const carregarPessoas = async () => {
    try {
      const resposta = await fetch(API_URL);
      if (resposta.ok) {
        const dados = await resposta.json();
        setCadastrados(dados);
        setApiOnline(true);
      }
    } catch {
      setApiOnline(false);
    }
  };

  useEffect(() => {
    carregarPessoas();
  }, []);

  const formatarData = (valor) => {
    return valor
      .replace(/\D/g, "")
      .slice(0, 8)
      .replace(/(\d{2})(\d)/, "$1/$2")
      .replace(/(\d{2})(\d)/, "$1/$2");
  };

  const formatarWpp = (valor) => {
    return valor
      .replace(/\D/g, "")
      .slice(0, 11)
      .replace(/(\d{2})(\d)/, "($1) $2")
      .replace(/(\d{5})(\d)/, "$1-$2");
  };

  const handleUploadFoto = (e) => {
    const arquivo = e.target.files?.[0];
    if (!arquivo) return;

    const leitor = new FileReader();
    leitor.onloadend = () => {
      setForm((prev) => ({ ...prev, foto: leitor.result }));
    };
    leitor.readAsDataURL(arquivo);
  };

  const removerFoto = () => {
    setForm((prev) => ({ ...prev, foto: "" }));
    if (inputFotoRef.current) inputFotoRef.current.value = "";
  };

  const validarFormulario = () => {
    const novosErros = {};
    if (!form.nome.trim()) novosErros.nome = "Por favor, preencha o nome";
    if (form.dtNascimento.length < 10)
      novosErros.dtNascimento = "Data inválida (DD/MM/AAAA)";
    if (!form.email.includes("@"))
      novosErros.email = "Informe um e-mail válido";
    if (form.wpp.replace(/\D/g, "").length < 10)
      novosErros.wpp = "Informe o WhatsApp com DDD";
    if (Number(form.genero) === 0) novosErros.genero = "Selecione o gênero";
    if (Number(form.tipoCadastro) === 0)
      novosErros.tipoCadastro = "Selecione o tipo";
    return novosErros;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const mapErros = validarFormulario();
    setErros(mapErros);

    if (Object.keys(mapErros).length === 0) {
      const payload = {
        ...form,
        cpf: "",
        genero: Number(form.genero),
        tipoCadastro: Number(form.tipoCadastro),
      };

      const url = editandoId ? `${API_URL}?id=${editandoId}` : API_URL;
      const metodo = editandoId ? "PUT" : "POST";

      try {
        const resposta = await fetch(url, {
          method: metodo,
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payload),
        });

        if (resposta.ok) {
          await carregarPessoas();
          setMensagemSucesso(
            editandoId
              ? "Cadastro atualizado no Supabase!"
              : "Salvo no Supabase com sucesso!",
          );
          setEditandoId(null);
          setForm(estadoInicial);
          if (inputFotoRef.current) inputFotoRef.current.value = "";
          setTimeout(() => setMensagemSucesso(""), 3500);
        }
      } catch {
        alert(
          "Não foi possível conectar na API Java (verifique se o ApiServer está rodando no Eclipse).",
        );
      }
    }
  };

  const iniciarEdicao = (item) => {
    setEditandoId(item.id);
    setErros({});
    setForm({
      nome: item.nome || "",
      dtNascimento: item.dtNascimento || "",
      email: item.email || "",
      wpp: item.wpp || "",
      genero: Number(item.genero) || 0,
      tipoCadastro: Number(item.tipoCadastro) || 0,
      foto: item.foto || "",
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleLimpar = () => {
    setEditandoId(null);
    setForm(estadoInicial);
    setErros({});
    if (inputFotoRef.current) inputFotoRef.current.value = "";
  };

  const removerRegistro = async (id) => {
    try {
      await fetch(`${API_URL}?id=${id}`, { method: "DELETE" });
      if (editandoId === id) handleLimpar();
      setCadastrados(cadastrados.filter((item) => item.id !== id));
    } catch {
      alert("Erro ao excluir registro no servidor Java.");
    }
  };

  const imprimirCracha = (item) => {
    setCrachaImpressao(item);
    setTimeout(() => {
      window.print();
      setCrachaImpressao(null);
    }, 100);
  };

  return (
    <div className="min-h-screen bg-slate-950 text-slate-100 py-10 px-4 sm:px-8 print:bg-white print:p-0">
      <div className="max-w-6xl mx-auto">
        <header className="mb-10 flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 border-b border-slate-800 pb-6 print:hidden">
          <div>
            <h1 className="text-3xl font-extrabold tracking-tight text-white">
              Cadastro & Emissão de Crachás
            </h1>
            <p className="text-slate-400 text-sm mt-1">
              Visualização em tempo real • Integrado com Java MVC/DAO + Supabase
            </p>
          </div>
          <span className="inline-flex items-center gap-2 text-xs font-medium bg-slate-900 border border-slate-800 px-3 py-1.5 rounded-full text-slate-300">
            <span
              className={`w-2 h-2 rounded-full ${
                apiOnline ? "bg-emerald-400 animate-pulse" : "bg-amber-400"
              }`}
            />
            {apiOnline
              ? "API Java + Supabase Conectados"
              : "Aguardando API Java (porta 8080)"}
          </span>
        </header>

        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start print:block">
          <form
            onSubmit={handleSubmit}
            className="lg:col-span-7 bg-slate-900/80 border border-slate-800 rounded-2xl p-6 sm:p-8 shadow-xl space-y-5 print:hidden"
          >
            <div className="flex items-center justify-between gap-2">
              <h2 className="text-lg font-semibold text-white">
                {editandoId
                  ? `Editando Cadastro #${editandoId}`
                  : "Dados da Pessoa"}
              </h2>
              {mensagemSucesso && (
                <span className="inline-flex items-center gap-1.5 text-xs font-medium text-emerald-400 bg-emerald-500/10 border border-emerald-500/30 px-3 py-1 rounded-full">
                  <CheckCircle2 className="w-3.5 h-3.5" />
                  {mensagemSucesso}
                </span>
              )}
            </div>

            <div>
              <label className="block text-xs font-medium text-slate-300 mb-1.5">
                FOTO DO CRACHÁ (OPCIONAL)
              </label>
              <div className="flex items-center gap-3">
                <input
                  ref={inputFotoRef}
                  type="file"
                  accept="image/*"
                  onChange={handleUploadFoto}
                  className="hidden"
                  id="upload-foto"
                />
                <label
                  htmlFor="upload-foto"
                  className="inline-flex items-center gap-2 bg-slate-950 hover:bg-slate-800 border border-slate-800 text-slate-200 text-xs font-medium px-4 py-2.5 rounded-xl cursor-pointer transition"
                >
                  <Camera className="w-4 h-4 text-indigo-400" />
                  {form.foto ? "Trocar foto" : "Escolher imagem"}
                </label>

                {form.foto && (
                  <button
                    type="button"
                    onClick={removerFoto}
                    className="inline-flex items-center gap-1 text-xs text-rose-400 hover:text-rose-300 px-2.5 py-2 rounded-lg bg-rose-500/10 transition cursor-pointer"
                  >
                    <X className="w-3.5 h-3.5" />
                    Remover foto
                  </button>
                )}
              </div>
            </div>

            <div>
              <label className="block text-xs font-medium text-slate-300 mb-1.5">
                NOME COMPLETO
              </label>
              <input
                type="text"
                value={form.nome}
                onChange={(e) => setForm({ ...form, nome: e.target.value })}
                placeholder="Ex: Luiz Felipe Andrade"
                className="w-full bg-slate-950 border border-slate-800 rounded-xl px-4 py-2.5 text-sm focus:outline-none focus:border-indigo-500 transition"
              />
              {erros.nome && (
                <p className="text-rose-400 text-xs mt-1">{erros.nome}</p>
              )}
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div>
                <label className="block text-xs font-medium text-slate-300 mb-1.5">
                  DATA DE NASCIMENTO
                </label>
                <input
                  type="text"
                  value={form.dtNascimento}
                  onChange={(e) =>
                    setForm({
                      ...form,
                      dtNascimento: formatarData(e.target.value),
                    })
                  }
                  placeholder="DD/MM/AAAA"
                  className="w-full bg-slate-950 border border-slate-800 rounded-xl px-4 py-2.5 text-sm focus:outline-none focus:border-indigo-500 transition"
                />
                {erros.dtNascimento && (
                  <p className="text-rose-400 text-xs mt-1">
                    {erros.dtNascimento}
                  </p>
                )}
              </div>

              <div>
                <label className="block text-xs font-medium text-slate-300 mb-1.5">
                  WHATSAPP
                </label>
                <input
                  type="text"
                  value={form.wpp}
                  onChange={(e) =>
                    setForm({ ...form, wpp: formatarWpp(e.target.value) })
                  }
                  placeholder="(21) 99999-9999"
                  className="w-full bg-slate-950 border border-slate-800 rounded-xl px-4 py-2.5 text-sm focus:outline-none focus:border-indigo-500 transition"
                />
                {erros.wpp && (
                  <p className="text-rose-400 text-xs mt-1">{erros.wpp}</p>
                )}
              </div>
            </div>

            <div>
              <label className="block text-xs font-medium text-slate-300 mb-1.5">
                E-MAIL
              </label>
              <input
                type="email"
                value={form.email}
                onChange={(e) => setForm({ ...form, email: e.target.value })}
                placeholder="contato@exemplo.com"
                className="w-full bg-slate-950 border border-slate-800 rounded-xl px-4 py-2.5 text-sm focus:outline-none focus:border-indigo-500 transition"
              />
              {erros.email && (
                <p className="text-rose-400 text-xs mt-1">{erros.email}</p>
              )}
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div>
                <label className="block text-xs font-medium text-slate-300 mb-1.5">
                  GÊNERO
                </label>
                <select
                  value={form.genero}
                  onChange={(e) =>
                    setForm({ ...form, genero: Number(e.target.value) })
                  }
                  className="w-full bg-slate-950 border border-slate-800 rounded-xl px-4 py-2.5 text-sm focus:outline-none focus:border-indigo-500 transition"
                >
                  <option value={0}>Selecione</option>
                  <option value={1}>Masculino</option>
                  <option value={2}>Feminino</option>
                </select>
                {erros.genero && (
                  <p className="text-rose-400 text-xs mt-1">{erros.genero}</p>
                )}
              </div>

              <div>
                <label className="block text-xs font-medium text-slate-300 mb-1.5">
                  TIPO DE CADASTRO
                </label>
                <select
                  value={form.tipoCadastro}
                  onChange={(e) =>
                    setForm({ ...form, tipoCadastro: Number(e.target.value) })
                  }
                  className="w-full bg-slate-950 border border-slate-800 rounded-xl px-4 py-2.5 text-sm focus:outline-none focus:border-indigo-500 transition"
                >
                  <option value={0}>Selecione</option>
                  <option value={1}>Aluno</option>
                  <option value={2}>Professor</option>
                </select>
                {erros.tipoCadastro && (
                  <p className="text-rose-400 text-xs mt-1">
                    {erros.tipoCadastro}
                  </p>
                )}
              </div>
            </div>

            <div className="pt-3 flex flex-wrap gap-3">
              <button
                type="submit"
                className={`flex-1 sm:flex-none inline-flex items-center justify-center gap-2 font-semibold text-sm px-6 py-3 rounded-xl shadow-lg transition cursor-pointer text-white ${
                  editandoId
                    ? "bg-emerald-600 hover:bg-emerald-500 shadow-emerald-600/20"
                    : "bg-indigo-600 hover:bg-indigo-500 shadow-indigo-600/20"
                }`}
              >
                {editandoId ? (
                  <Save className="w-4 h-4" />
                ) : (
                  <UserPlus className="w-4 h-4" />
                )}
                {editandoId ? "SALVAR ALTERAÇÕES" : "CADASTRAR"}
              </button>
              <button
                type="button"
                onClick={handleLimpar}
                className="inline-flex items-center justify-center gap-2 bg-slate-800 hover:bg-slate-700 text-slate-200 font-medium text-sm px-5 py-3 rounded-xl transition cursor-pointer"
              >
                <RotateCcw className="w-4 h-4" />
                {editandoId ? "CANCELAR EDIÇÃO" : "LIMPAR"}
              </button>
            </div>
          </form>

          <div className="lg:col-span-5 bg-slate-900/40 border border-slate-800/80 rounded-2xl p-6 flex flex-col items-center justify-center print:bg-transparent print:border-none print:p-0 print:min-h-screen">
            <span className="text-xs uppercase tracking-widest text-slate-400 mb-4 font-semibold print:hidden">
              Pré-visualização do Crachá
            </span>
            <BadgePreview dados={crachaImpressao || form} />
          </div>
        </div>

        {cadastrados.length > 0 && (
          <section className="mt-12 print:hidden">
            <h2 className="text-lg font-semibold text-white mb-4">
              Crachás Emitidos ({cadastrados.length})
            </h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
              {cadastrados.map((item) => (
                <div
                  key={item.id}
                  className={`bg-slate-900 border rounded-xl p-4 flex items-center justify-between gap-3 transition ${
                    editandoId === item.id
                      ? "border-emerald-500/60"
                      : "border-slate-800"
                  }`}
                >
                  <div className="flex items-center gap-3 min-w-0">
                    {item.foto ? (
                      <img
                        src={item.foto}
                        alt={item.nome}
                        className="w-10 h-10 rounded-full object-cover border border-slate-700 shrink-0"
                      />
                    ) : (
                      <div className="w-10 h-10 rounded-full bg-slate-800 flex items-center justify-center text-xs font-bold text-slate-300 shrink-0">
                        {item.nome.trim().slice(0, 2).toUpperCase()}
                      </div>
                    )}

                    <div className="min-w-0">
                      <span
                        className={`text-[10px] font-bold uppercase px-2 py-0.5 rounded ${
                          item.tipoCadastro === 2
                            ? "bg-emerald-500/20 text-emerald-300"
                            : "bg-indigo-500/20 text-indigo-300"
                        }`}
                      >
                        {item.tipoCadastro === 2 ? "Professor" : "Aluno"}
                      </span>
                      <h4 className="font-semibold text-sm text-white truncate mt-1">
                        {item.nome}
                      </h4>
                      <p className="text-xs text-slate-400 truncate">
                        {item.email}
                      </p>
                    </div>
                  </div>

                  <div className="flex items-center gap-1 shrink-0">
                    <a
                      href={`https://wa.me/55${item.wpp.replace(/\D/g, "")}`}
                      target="_blank"
                      rel="noreferrer"
                      title="Chamar no WhatsApp"
                      className="p-2 rounded-lg bg-emerald-500/10 text-emerald-400 hover:bg-emerald-500/20 transition"
                    >
                      <MessageCircle className="w-4 h-4" />
                    </a>
                    <button
                      onClick={() => iniciarEdicao(item)}
                      title="Editar Cadastro"
                      className="p-2 rounded-lg bg-indigo-500/10 text-indigo-400 hover:bg-indigo-500/20 transition cursor-pointer"
                    >
                      <Pencil className="w-4 h-4" />
                    </button>
                    <button
                      onClick={() => imprimirCracha(item)}
                      title="Imprimir Crachá"
                      className="p-2 rounded-lg bg-slate-800 text-slate-300 hover:bg-slate-700 transition cursor-pointer"
                    >
                      <Printer className="w-4 h-4" />
                    </button>
                    <button
                      onClick={() => removerRegistro(item.id)}
                      title="Remover"
                      className="p-2 rounded-lg bg-rose-500/10 text-rose-400 hover:bg-rose-500/20 transition cursor-pointer"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                </div>
              ))}
            </div>
          </section>
        )}
      </div>
    </div>
  );
}
