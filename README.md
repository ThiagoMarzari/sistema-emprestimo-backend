
 Sistema de Empréstimo - UFN  
> Sistema para controle de **itens emprestados** e **devolução** na **Universidade Franciscana (UFN)**.

---
```

---

## 📌 Funcionalidades Principais

### 📦 Portaria UFN
- Cadastro e gerenciamento de **itens** (ex: chaves, controles de ar).
- Cadastro e gerenciamento de **usuários** (professores e colaboradores da UFN).
- **Leitura de código de barras** para:
  - Identificação de itens no empréstimo/devolução.
  - Identificação de usuários.
- **Registro de movimentações** com:
  - Data, hora, item e usuário.
- **Painel de acompanhamento**:
  - Situação atual de todos os itens (disponível ou emprestado).

---

## 📋 Endpoints da API

### 🧾 Itens

#### 🔹 `GET /items`  
🔍 Lista todos os itens cadastrados.
```json
[
  {
    "id": 1,
    "nome": "Chave-203",
    "codigo": "CH-203"
  }
]
```

#### 🔹 `POST /items`  
➕ Cadastra um novo item.  
**Headers:**  
`Content-Type: application/json`  
**Body:**
```json
{
  "nome": "Chave-203",
  "codigo": "CH-203"
}
```
**Resposta:**
```json
{
  "id": 2,
  "nome": "Chave-203",
  "codigo": "CH-203"
}
```

#### 🔹 `POST /itens/emprestar`  
📤 Registra um empréstimo.
```json
{
  "itemCodigo": "CH-201",
  "usuarioCodigo": "PRC-241zzz"
}
```
**Resposta:**
```json
{
  "mensagem": "Item emprestado com sucesso."
}
```

#### 🔹 `POST /itens/devolver`  
📥 Registra a devolução de um item.
```json
{
  "itemCodigo": "CH-201"
}
```
**Resposta:**
```json
{
  "mensagem": "Item devolvido com sucesso."
}
```

#### 🔹 `GET /itens/emprestados`  
📌 Lista itens atualmente emprestados.
```json
[
  {
    "itemCodigo": "CH-201",
    "usuarioCodigo": "PRC-241zzz",
    "dataEmprestimo": "2025-06-23T14:00:00"
  }
]
```

---

### 👤 Usuários

#### 🔹 `POST /usuarios`  
➕ Cadastra um novo usuário.
```json
{
  "nome": "Thiago",
  "codigo": "PRF-2312x"
}
```
**Resposta:**
```json
{
  "id": 1,
  "nome": "Thiago",
  "codigo": "PRF-2312x"
}
```

#### 🔹 `GET /usuarios`  
🔍 Lista todos os usuários cadastrados.
```json
[
  {
    "id": 1,
    "nome": "Thiago",
    "codigo": "PRF-2312x"
  }
]
```

---

### 📜 Logs

#### 🔹 `GET /logs`  
🕓 Retorna o histórico de empréstimos e devoluções.

---

## 🧪 Postman  
Você pode importar todas as requisições usando o seguinte arquivo:

```
UFN - Controle Itens.postman_collection.json
```

---

## 📊 Diagramas Utilizados

### ✅ Diagrama de Casos de Uso
*(Adicionar imagem ou link aqui)*

### ✅ Diagrama de Classes
*(Adicionar imagem)*

---

## 📁 Estrutura de Pastas - Backend

> Visualização da organização interna do projeto:

![Estrutura de Pastas](https://github.com/user-attachments/assets/26a1136a-01f8-4ca0-9016-b24dd10d7c1d)

---

## 👨‍💻 Autores

**Thiago Marzari**  
GitHub: [@ThiagoMarzari](https://github.com/ThiagoMarzari)

**Gabriel Saccol**  
GitHub: [@Gabrielzinho1518](https://github.com/Gabrielzinho1518)

**Gabriel Pinheiro**  
GitHub: [@gabriel99557](https://github.com/gabriel99557) 

**Gabriel Machado**  
Github: [@Gorling](https://github.com/Gorling)


