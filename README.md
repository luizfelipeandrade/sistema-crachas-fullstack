# 🪪 Sistema de Cadastro & Emissão de Crachás | Badge Management System

[🇧🇷 Português](#-português) | [🇺🇸 English](#-english)

---

## 🇧🇷 Português

Aplicação Full-Stack para cadastro de alunos e professores e emissão dinâmica de crachás institucionais com foto, QR Code vCard e impressão individual.

O projeto nasceu como uma aplicação desktop em Java (Swing + MVC/DAO) e evoluiu para uma arquitetura web moderna desacoplada, mantendo o núcleo orientado a objetos em Java puro servindo uma API REST nativa conectada a um banco de dados PostgreSQL na nuvem.

### ✨ Funcionalidades

- **Pré-visualização em tempo real:** O crachá é atualizado instantaneamente enquanto os dados são digitados, alterando cores e identificação entre **Aluno** e **Professor**.
- **Upload de Foto & QR Code Dinâmico:** Suporte a foto de perfil (convertida em Base64) e geração automática de QR Code com os dados de contato.
- **CRUD Completo:** Cadastro (`POST`), listagem (`GET`), edição (`PUT`) e exclusão (`DELETE`) persistidos em banco relacional.
- **Impressão Individual & Contato Rápido:** Layout `@media print` otimizado para imprimir apenas o crachá selecionado e botão direto para contato via WhatsApp.

### 🛠️ Tecnologias Utilizadas

- **Front-end:** React 19, Vite, Tailwind CSS, Lucide Icons, `qrcode.react`
- **Back-end:** Java 17 (API REST construída com `com.sun.net.httpserver.HttpServer`, arquitetura MVC/DAO e JDBC puro, sem frameworks externos)
- **Banco de Dados:** PostgreSQL hospedado no **Supabase**
- **Infraestrutura & Deploy:** Docker, **Render** (Back-end) e **Vercel** (Front-end)

### 📁 Estrutura do Projeto

- **`BackEnd-Java/`** — API REST nativa (`ApiServer.java`), camada DAO (`PessoaDao.java`), Modelos, driver JDBC em `lib/` e `Dockerfile`
- **`FrontEnd-React/`** — Interface Web em React + Tailwind CSS (`src/App.jsx`, `src/components/BadgePreview.jsx`, `index.html`)

### ⚙️ Como Executar Localmente

#### 1. Back-end (Java)

1. Configure as credenciais do PostgreSQL em `ConexaoMySQL.java` (ou defina as variáveis de ambiente `DB_URL`, `DB_USER` e `DB_PASSWORD`).
2. Adicione o driver JDBC do PostgreSQL (`postgresql-42.7.x.jar`) ao Build Path / pasta `lib/`.
3. Execute a classe `br.com.projetoorientadoobjeto.main.ApiServer` (iniciará na porta `8080`).

#### 2. Front-end (React)

1. Acesse a pasta `FrontEnd-React` pelo terminal.
2. Execute `npm install` para instalar as dependências.
3. Execute `npm run dev` e acesse `http://localhost:5173` no navegador.

---

## 🇺🇸 English

Full-Stack web application for registering students and teachers and generating institutional ID badges with profile photo upload, dynamic QR Code, and dedicated print layout.

Originally built as a Java desktop application (Swing + MVC/DAO), this project was re-architected into a modern decoupled web system, preserving its pure Object-Oriented Java core to serve a native REST API connected to a cloud PostgreSQL database.

### ✨ Key Features

- **Real-Time Badge Preview:** Live visual updates as you type, dynamically switching themes and labels between **Student** and **Teacher**.
- **Photo Upload & Dynamic QR Code:** Profile image upload (Base64 encoded) and automatic QR Code generation with contact details.
- **Full CRUD Operations:** Create (`POST`), Read (`GET`), Update (`PUT`), and Delete (`DELETE`) persisted in a relational database.
- **Dedicated Print Layout & Quick Contact:** Clean `@media print` styling to print individual badges and one-click WhatsApp integration.

### 🛠️ Tech Stack

- **Front-end:** React 19, Vite, Tailwind CSS, Lucide Icons, `qrcode.react`
- **Back-end:** Java 17 (Native REST API built with `com.sun.net.httpserver.HttpServer`, MVC/DAO architecture, and pure JDBC)
- **Database:** PostgreSQL hosted on **Supabase**
- **Cloud & Deployment:** Docker, **Render** (Back-end), and **Vercel** (Front-end)

### 📁 Project Structure

- **`BackEnd-Java/`** — Native Java REST API (`ApiServer.java`), DAO layer (`PessoaDao.java`), Models, JDBC driver inside `lib/`, and `Dockerfile`
- **`FrontEnd-React/`** — React + Tailwind CSS Web Interface (`src/App.jsx`, `src/components/BadgePreview.jsx`, `index.html`)

### ⚙️ Running Locally

#### 1. Back-end (Java)

1. Set your PostgreSQL credentials in `ConexaoMySQL.java` (or configure `DB_URL`, `DB_USER`, and `DB_PASSWORD` environment variables).
2. Ensure the PostgreSQL JDBC driver (`postgresql-42.7.x.jar`) is in your Build Path / `lib/` folder.
3. Run `br.com.projetoorientadoobjeto.main.ApiServer` (starts on port `8080`).

#### 2. Front-end (React)

1. Navigate to the `FrontEnd-React` directory in your terminal.
2. Run `npm install` to install dependencies.
3. Run `npm run dev` and open `http://localhost:5173` in your browser.

---

Developed by **Luiz Felipe Andrade** • [LinkedIn](https://www.linkedin.com/in/lfandrade)
