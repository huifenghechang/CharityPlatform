/**
 * Created by swing on 2018/3/5.
 */
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})

var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于' + new Date().toLocaleString()
    }
})

var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: false
    }
})

var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: '学习JavaScript'},
            { text: '学习vue'},
            { text: '整个牛项目'}
        ]
    }
})

var app5 = new Vue({
    el: '#app-5',
    data: {
        message:"Hello Vue.js!"
    },
    methods: {
        reverseMessage: function (){
            this.message = this.message.split('').reverse().join('')
        }
    }
})


var app6 = new Vue({
    el: '#app-6',
    data: {
        message:"Hello Vue!"
    }
})

