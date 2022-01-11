const bodyParser = require('body-parser');
const express = require('express');
const path = require('path');
const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: true}));
app.use(bodyParser.json());

app.get('/hello', (req, res) => {
    res.send('Hello World!');
});

app.get('/form', (req, res) => {
    res.sendFile(path.join(__dirname, '/as8.html'));
});

app.post('/formdata', (req, res) => {
    res.send('<h1>Student ' + req.body.fname + ' ' + req.body.lname+ ' has s-number ' + req.body.snumber + '</h1>');
});

app.post('/jsondata', (req, res) => {

    res.send('<h1>Student ' + req.body.fname + ' ' + req.body.lname+ ' has s-number ' + req.body.snumber + '</h1>');
});
app.listen(port, () => {
    console.log(`Server works at http://localhost:${port}`);
});
