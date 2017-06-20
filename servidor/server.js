var express     = require("express");
var bodyParser  = require("body-parser");
var app         = express();


app.use(bodyParser.urlencoded({ extended:false }));

app.use(bodyParser.json());


var carros = [];

app.get('/carros',function(req,res){
    res.send(carros);

});

app.post('/carros',function(req,res){
    console.log(req);
    carros.push(req.body);
    res.send(201);
});


app.listen(3000,function(){
    console.log('Servidor inciado na porta 3000');
});

 