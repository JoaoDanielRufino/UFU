const fs = require('fs');
const blockexplorer = require('blockchain.info/blockexplorer');

const MAX_BLOCK_HEIGHT = 200;
const FILE_NAME = 'transactions.txt';

const getBlock = async () => {
  for (let i = 0; i < MAX_BLOCK_HEIGHT; i++) {
    const { blocks } = await blockexplorer.getBlockHeight(i);
    let details;

    blocks.forEach(block => {
      console.log(`Height: ${block.height}`);
      details = `Block Height: ${block.height}\n`;

      const { tx } = block; // Transaction
      tx.forEach(transaction => {
        details += `  Transaction index: ${transaction.tx_index}\n  Transaction weight: ${transaction.weight}\n`;

        transaction.inputs.forEach(input => {
          details += `    Input sequence: ${input.sequence}\n`;
          if (input.value != undefined) {
            details += `   Input witness: ${input.witness}\n`;
            details += `   Input address: ${input.addr}\n    Input value: ${input.value}\n`;
            details += `   Input transaction index: ${input.tx_index}\n`;
          }
        });

        transaction.out.forEach(out => {
          details += `    Output transaction index: ${out.tx_index}\n`;
          details += `    Output value: ${out.value}\n`;
          details += `    Output address: ${out.addr}\n`;
        });
      });
    });
    fs.appendFileSync(FILE_NAME, details);
  }
}

fs.writeFileSync(FILE_NAME, '');

getBlock();
