import { createRouter, createWebHistory } from 'vue-router'

const routes: any[] = [
    {
        path: '/',
        name: 'home',
        component: () => import('~/components/HelloWorld.vue')
    }, {
        path: '/guide',
        name: 'guide',
        component: () => import('~/components/Guide.vue')
    }
]

export default createRouter({
    history: createWebHistory(),
    routes,
})
