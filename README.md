# Sistema de Empréstimo - UFN [Controle de Itens]

Sistema para gerenciamento de **emprestimos** e **devolução** na Universidade Franciscana.

---

Rotas do backend e sua funcionalidade: 

## 🌐 Base URL
```
http://localhost:8080
```

---

## 📋 Endpoints

### 🧾 Itens

#### 🔹 GET `/items`
- **Descrição:** Retorna todos os itens cadastrados.
- **Resposta esperada:**
```json
[
  {
    "id": 1,
    "nome": "Chave-203",
    "codigo": "CH-203"
  }
]
```

#### 🔹 POST `/items`
- **Descrição:** Cria um novo item.
- **Headers:**
  ```
  Content-Type: application/json
  ```
- **Body (JSON):**
```json
{
  "nome": "Chave-203",
  "codigo": "CH-203"
}
```
- **Resposta esperada:**
```json
{
  "id": 2,
  "nome": "Chave-203",
  "codigo": "CH-203"
}
```

#### 🔹 POST `/itens/emprestar`
- **Descrição:** Registra o empréstimo de um item para um usuário.
- **Body (JSON):**
```json
{
  "itemCodigo": "CH-201",
  "usuarioCodigo": "PRC-241zzz"
}
```
- **Resposta esperada:**
```json
{
  "mensagem": "Item emprestado com sucesso."
}
```

#### 🔹 POST `/itens/devolver`
- **Descrição:** Registra a devolução de um item.
- **Body (JSON):**
```json
{
  "itemCodigo": "CH-201"
}
```
- **Resposta esperada:**
```json
{
  "mensagem": "Item devolvido com sucesso."
}
```

#### 🔹 GET `/itens/emprestados`
- **Descrição:** Lista os itens que estão emprestados.
- **Resposta esperada:**
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

#### 🔹 POST `/usuarios`
- **Descrição:** Cria um novo usuário.
- **Body (JSON):**
```json
{
  "nome": "Thiago",
  "codigo": "PRF-2312x"
}
```
- **Resposta esperada:**
```json
{
  "id": 1,
  "nome": "Thiago",
  "codigo": "PRF-2312x"
}
```

#### 🔹 GET `/usuarios`
- **Descrição:** Lista todos os usuários cadastrados.
- **Resposta esperada:**
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

#### 🔹 GET `/logs`
- **Descrição:** Retorna os logs do sistema (ex: histórico de empréstimos e devoluções).

---

## 📥 Importar no Postman

Você pode importar a coleção de requisições no Postman usando o arquivo:

```
UFN - Controle Itens.postman_collection.json
```


## Funcionalidades: 

## Portaria UFN
- Cadastro e gerenciamento de itens (ex: chaves, controles de ar).
- Cadastro e gerenciamento de usuários (professores e colaboradores da UFN).
- Leitura de códigos de barras para:
- Identificar os itens no momento do empréstimo e devolução.
- Identificar o usuário responsável pelo item.
- Registro das movimentações (empréstimo e devolução) com data, hora, item e usuário.
- Painel de acompanhamento com a situação atual dos itens (disponível ou emprestado).

## 🧠 Diagramas Utilizados:

## Diagrama de Caso de Uso:

## Diagramas de Classe:

## 📁 Estrutura de Pastas -> backend

![WhatsApp Image 2025-06-23 at 13 09 34](https://github.com/user-attachments/assets/26a1136a-01f8-4ca0-9016-b24dd10d7c1d)

## 👨‍💻 Autores

**Thiago Marzari**  
GitHub: [@ThiagoMarzari](https://github.com/ThiagoMarzari)

**Gabriel Saccol**  
GitHub: [@Gabrielzinho1518](https://github.com/Gabrielzinho1518)

**Gabriel Pinheiro**  
GitHub: [@gabriel99557](https://github.com/gabriel99557) 

**Gabriel Machado** 

Github: [@Gorling](https://github.com/Gorling)
