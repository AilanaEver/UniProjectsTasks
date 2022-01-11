const http = require('http')
const url = require('url')

const server = http.createServer(function (req, resp) {
  try {
    const [op, num1, num2] = parseRequest(req)
    const result = calculate(op, num1, num2)
    respondCalculationResult(resp, op, num1, num2, result)
  } catch (e) {
    respError(resp, e)
  }
})

function respError (resp, error) {
  resp.writeHead(400, { 'Content-type': 'text/plain' })
  resp.end(error.toString())
}

function parseRequest (req) {
  const parsedUrl = url.parse(req.url, true)
  const op = parseOperation(parsedUrl.pathname)
  const num1 = Number(parsedUrl.query.num1)
  const num2 = Number(parsedUrl.query.num2)
  checkParameter(num1, 'num1')
  checkParameter(num2, 'num2')
  return [op, num1, num2]
}

function parseOperation (op) {
  switch (op) {
    case '/add':
      return '+'
    case '/sub':
      return '-'
    case '/mul':
      return '*'
    case '/div':
      return '/'
    default:
      throw Error('Not found')
  }
}

function checkParameter (parameter, parameterName) {
  if (isNaN(parameter)) throw Error(parameterName + ' is not a number')
}

function calculate (op, num1, num2) {
  switch (op) {
    case '+':
      return num1 + num2
    case '-':
      return num1 - num2
    case '*':
      return num1 * num2
    case '/':
      if (num2 === 0) throw Error('Cannot divide by zero')
      return num1 / num2
    default:
      throw Error('Not supported operation')
  }
}

function respondCalculationResult (resp, op, num1, num2, res) {
  resp.writeHead(200, { 'Content-type': 'text/plain' })
  resp.end(num1 + op + num2 + '=' + res)
}

server.listen(8000, '127.0.0.1')
console.log('Server running at http://127.0.0.1:8000/')
