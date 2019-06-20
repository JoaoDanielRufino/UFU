const express = require('express');

const LanguageTranslatorV3 = require('ibm-watson/language-translator/v3');

const languageTranslator = new LanguageTranslatorV3({
  iam_apikey: '<MyApiKey>',
  url: 'https://gateway.watsonplatform.net/language-translator/api/',
  version: '2019-02-01'
});

const app = express();

app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET');
  res.setHeader('Access-Control-Allow-Headers', 'content-type');
  res.setHeader('Access-Control-Allow-Credentials', true);
  next();
});

app.get('/translate', (req, res) => {
  const { text, language } = req.query;

  let target = '';
  switch (language) {
    case 'English': target = 'en'; break;
    case 'Portuguese': target = 'pt'; break;
    case 'Spanish': target = 'es'; break;
    case 'French': target = 'fr'; break;
    case 'Italian': target = 'it'; break;
    case 'German': target = 'de'; break;
    default: target = 'en';
  }

  if (target == 'en') {
    res.send({ translation: text });
  }
  else {
    languageTranslator.translate({ text, source: 'en', target }, (err, translation) => {
      if (err) {
        console.log(err);
      }
      else {
        res.send({ translation: translation.translations[0].translation });
      }
    });
  }
});

app.listen(8080, () => {
  console.log("Server on");
});
