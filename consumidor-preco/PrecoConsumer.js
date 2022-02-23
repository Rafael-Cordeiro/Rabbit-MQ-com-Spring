const amqp = require('amqplib')
const queue = 'PRECO'

amqp.connect({
    host: 'localhost',
    port: 5672,
    username: 'admin',
    password: 'admin'
}).then((connection) => {
    connection.createChannel()
    .then((channel) => {
        channel.consume(queue, (message) => {
            console.log(message.content.toString())
        }, {noAck: true})
    })
    .catch((error) => console.log(error))
}).catch((error) => console.log(error))