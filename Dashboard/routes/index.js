const nodemailer = require('nodemailer');
var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

/*const name = "game story";
const user = "gamestorysuporte@gmail.com";
const pass = "gamestory20042002";

router.get('/send', (req, res) => {
  const transporter = nodemailer.createTransport({
    host: "smtp.gmail.com",
    port: 587,
    auth: {name, user, pass}
  });

  transporter.sendMail({
    from: user,
    to: "alessandroraul@hotmail.com",
    replyTo: "alessandro.guedes@bandtec.com.br",
    subject: "Recuperação de senha",
    text: "Seu codigo de redefinição é 1598",
  }).then(info =>{
    res.send(info)
  }).catch(error =>{
    res.send(error)
  })
})*/

module.exports = router;
