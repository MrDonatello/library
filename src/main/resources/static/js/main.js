

Vue.prototype.$http = axios;
Vue.component('author-row', {
    props: ['author'],
    template: '<div><i>({{ author.id }})</i> {{ author.firstName }}, {{author.lastName}}, {{author.patronymic}}, {{author.biography}}, {{author.yearOfBirth}}</div>'
});

Vue.component('author-list', {

    props: ['authors'],
    template: '<div><author-row v-for="author in authors"  :key="author.id" :author="author" /></div>',
    created() {
        var vm = this;
        this.$http
            .get('library/author{/id}')
            .then(res => res.json().then(data =>
                    //console.log(data)
                    data.forEach(author => this.authors.push(author))
                )
            )
    }
});

var app = new Vue({
    el: '#app',
    template: '<author-list :authors="authors" />',
    data: {
        authors: []
    }
});
