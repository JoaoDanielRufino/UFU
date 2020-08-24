Prática - 04:

Requisitos:
    - Python 3.6+
    - pip ou pip3
Como executar:
    -1º Criar um ambiente: python3 -m venv venv 
        - a) Para ativar o ambiente em linux (Ubuntu): source ./venv/bin/activate
        - b) Para ativar o ambiente em Windows: venv\Scripts\activate
    -2º Instalar as dependências: pip3 install -r requirements
    -3º Para executar o programa:
        - a) Para usar a distância euclidiana: python3 main.py euclidean ./dataset/iris.data
        - b) Para usar a distância de manhattan: python3 main.py manhattan ./dataset/iris.data
        OBS: Onde está ./dataset/iris.data pode ser usado qualquer outro path para seu dataset
