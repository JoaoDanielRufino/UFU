const crypto = require('crypto');

const map = new Map();

const verifyRange = 10;

let iteracoes = 0;
while(true) {
  const randomString = crypto.randomBytes(12).toString('hex');
  const hash = crypto.createHash('md5').update(randomString).digest('hex');
  const subHash = hash.substring(0, verifyRange);  // Considerando somente uma parte do hash para a colisao

  if(map.has(subHash)) {
    console.log(`Colisao na iteracao ${iteracoes}`);
    console.log(`String da iteracao: ${randomString}`);
    console.log(`String armazenada no hash: ${map.get(subHash)}`);
    console.log(`Hash obtido: ${hash}`);
    break;
  }

  map.set(subHash, randomString);
  iteracoes++;
}
