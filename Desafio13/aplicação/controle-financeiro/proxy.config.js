const proxy = [{context: '/api', target: 'http://localhost:8080', pathRewrite: {'http://localhost:4200/'
: 'http://localhost:8080/'}}]; module.exports = proxy;