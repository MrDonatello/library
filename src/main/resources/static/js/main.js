/*
var authorApi = this.$resource('library/author{/id}');
//var author = axios.get('library/author{/id}').th

Vue.component('author-row', {
    props: ['author'],
    template: '<div><i>({{ author.id }})</i> {{ author.text }}</div>'
});

Vue.component('author-list', {

    props: ['authors'],
    template: '<div><author-row v-for="author in authors"  :key="author.id" :author="author" /></div>',
    created: function () {
 authorApi.get()
    }
});

var app = new Vue({
    el: '#app',
    template: '<author-list :authors="authors" />',
    data: {
        authors: []
    }, mounted(){
        axios.get('library/author{/id}').then(response => this.data = response.data)
    }


});
*/
