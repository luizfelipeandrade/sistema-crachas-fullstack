import { QRCodeSVG } from "qrcode.react";
import {
  GraduationCap,
  BookOpen,
  Phone,
  Mail,
  Calendar,
  UserCheck,
} from "lucide-react";

export function BadgePreview({ dados }) {
  const isProfessor = Number(dados.tipoCadastro) === 2;
  const isAluno = Number(dados.tipoCadastro) === 1;

  const generoTexto =
    {
      1: "Masculino",
      2: "Feminino",
    }[Number(dados.genero)] || "Não informado";

  const tipoTexto = isProfessor
    ? "Professor"
    : isAluno
      ? "Aluno"
      : "Não definido";

  const tema = isProfessor
    ? {
        gradiente: "from-emerald-700 via-teal-800 to-slate-900",
        badge: "bg-emerald-400/20 text-emerald-200 border-emerald-400/40",
        titulo: "CORPO DOCENTE • PROFESSOR",
        icone: <BookOpen className="w-4 h-4" />,
        borda: "border-emerald-500/30",
      }
    : {
        gradiente: "from-blue-700 via-indigo-800 to-slate-900",
        badge: "bg-blue-400/20 text-blue-200 border-blue-400/40",
        titulo: isAluno
          ? "CARTEIRA ESTUDANTIL • ALUNO"
          : "IDENTIFICAÇÃO ACADÊMICA",
        icone: <GraduationCap className="w-4 h-4" />,
        borda: "border-indigo-500/30",
      };

  const codigoId = `ID-${(dados.nome || "USR").substring(0, 3).toUpperCase()}-${
    dados.dtNascimento
      ? dados.dtNascimento.replace(/\D/g, "").slice(-4)
      : "2026"
  }`;

  // Conteúdo real codificado dentro do QR Code
  const conteudoQrCode = JSON.stringify({
    id: codigoId,
    nome: dados.nome || "Pendente",
    tipo: tipoTexto,
    email: dados.email || "",
    status: "ATIVO-2026",
  });

  return (
    <div className="flex flex-col items-center p-2 print:p-3 print:border print:border-dashed print:border-slate-400 print:rounded-3xl">
      {/* Cartão Principal */}
      <div
        className={`w-full max-w-sm rounded-2xl bg-gradient-to-br ${tema.gradiente} text-white shadow-2xl print:shadow-none border ${tema.borda} overflow-hidden transition-all duration-300`}
      >
        {/* Furo do cordão integrado ao topo do crachá */}
        <div className="pt-3.5 pb-1 flex justify-center">
          <div
            title="Perfuração do cordão"
            className="w-16 h-3 bg-slate-950/90 print:bg-white rounded-full border border-white/25 print:border-dashed print:border-slate-400 shadow-inner"
          />
        </div>

        {/* Cabeçalho Institucional */}
        <div className="px-6 pt-3 pb-4 border-b border-white/10 flex items-center justify-between">
          <div>
            <p className="text-[10px] uppercase tracking-widest text-white/60 font-semibold">
              Sistema Acadêmico • POO
            </p>
            <div
              className={`mt-1 inline-flex items-center gap-1.5 px-2.5 py-0.5 rounded-full text-xs font-medium border ${tema.badge}`}
            >
              {tema.icone}
              <span>{tema.titulo}</span>
            </div>
          </div>
          <span className="text-xs font-mono text-white/70 bg-black/25 px-2 py-1 rounded">
            {codigoId}
          </span>
        </div>

        {/* Corpo com Foto/Iniciais e Dados */}
        <div className="p-6 flex flex-col items-center text-center">
          <div className="w-24 h-24 rounded-full bg-white/10 border-2 border-white/40 flex items-center justify-center text-3xl font-bold tracking-wider shadow-lg mb-4 backdrop-blur-sm overflow-hidden">
            {dados.foto ? (
              <img
                src={dados.foto}
                alt={dados.nome || "Foto do Crachá"}
                className="w-full h-full object-cover"
              />
            ) : dados.nome ? (
              dados.nome
                .trim()
                .split(" ")
                .slice(0, 2)
                .map((n) => n[0])
                .join("")
                .toUpperCase()
            ) : (
              "?"
            )}
          </div>

          <h3 className="text-xl font-bold tracking-tight line-clamp-1">
            {dados.nome || "Nome Completo"}
          </h3>
          <p className="text-xs text-white/60 mt-0.5">Gênero: {generoTexto}</p>

          {/* Informações de Contato */}
          <div className="w-full mt-5 space-y-2 text-left bg-black/20 rounded-xl p-3.5 text-xs border border-white/10">
            <div className="flex items-center gap-2 text-white/85">
              <Mail className="w-3.5 h-3.5 text-white/50 shrink-0" />
              <span className="truncate">
                {dados.email || "email@instituicao.edu.br"}
              </span>
            </div>
            <div className="flex items-center gap-2 text-white/85">
              <Phone className="w-3.5 h-3.5 text-white/50 shrink-0" />
              <span>{dados.wpp || "(00) 00000-0000"}</span>
            </div>
            <div className="flex items-center gap-2 text-white/85">
              <Calendar className="w-3.5 h-3.5 text-white/50 shrink-0" />
              <span>Nasc.: {dados.dtNascimento || "DD/MM/AAAA"}</span>
            </div>
          </div>
        </div>

        {/* Rodapé com QR Code Dinâmico */}
        <div className="px-6 py-3.5 bg-black/30 border-t border-white/10 flex items-center justify-between">
          <div className="flex items-center gap-2 text-[11px] text-emerald-300">
            <UserCheck className="w-4 h-4" />
            <span>Cadastro Ativo • 2026</span>
          </div>
          <div
            className="bg-white p-1.5 rounded-lg shadow-sm"
            title="Escaneie para validar o crachá"
          >
            <QRCodeSVG
              value={conteudoQrCode}
              size={44}
              bgColor="#ffffff"
              fgColor="#0f172a"
              level="M"
            />
          </div>
        </div>
      </div>

      {/* Legenda de corte visível apenas na impressão */}
      <span className="hidden print:block text-[10px] text-slate-400 mt-2 tracking-wide">
        ✂ Recorte na linha pontilhada • Perfure o topo para o cordão
      </span>
    </div>
  );
}
