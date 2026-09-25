# 📊 Painel de Inteligência de Mercado

Aplicação Full-Stack de inteligência de mercado desenvolvida para análise demográfica e identificação de potencial de mercado em municípios brasileiros, consumindo dados oficiais da API do IBGE.

---

## 🚀 Tecnologias Utilizadas

### **Backend**
- **Java 17**
- **Spring Boot 3** (Spring Data JPA, Spring Web)
- **H2 Database** (Banco de dados em memória para execução simplificada)
- **Maven** (Gerenciador de dependências e build)

### **Frontend**
- **React 18** + **Vite**
- **Axios** (Consumo de APIs REST)
- **Recharts** (Visualização de dados e gráficos)
- **Lucide React** (Iconografia)

---

## ⚙️ Pré-requisitos
Antes de começar, certifique-se de ter instalado em sua máquina:
- **JDK 17** ou superior
- **Node.js** (v18 ou superior) e **npm**
- **Git**

---

## 📦 Como Executar o Projeto

Você pode rodar toda a aplicação diretamente pelo **VS Code** ou terminal.

### **1. Clonar o Repositório**
```bash
git clone [https://github.com/victormaximinodesouza/Painel_Intelig-ncia_Mercado.git](https://github.com/victormaximinodesouza/Painel_Intelig-ncia_Mercado.git)
cd Painel_Intelig-ncia_Mercado

## 🏗️ Arquitetura e Fluxo de Dados

```text
       ┌───────────────────────────────┐
       │     API Pública do IBGE       │
       └──────────────┬────────────────┘
                      │ GET (Data Pipeline no App Startup)
                      ▼
       ┌───────────────────────────────┐
       │   Spring Boot Backend (8080)  │
       │   ├── IbgeService             │
       │   ├── MarketPotentialEngine   │
       │   └── H2 Database (In-Memory) │
       └──────────────┬────────────────┘
                      │ REST API / JSON (CORS Enabled)
                      ▼
       ┌───────────────────────────────┐
       │     React + Vite Frontend     │
       │   ├── Axios Service           │
       │   ├── Interactive Charts      │
       │   └── State/UF Filter Engine  │
       └───────────────────────────────┘ 
       
