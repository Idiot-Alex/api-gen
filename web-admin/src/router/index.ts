import { createRouter, createWebHistory } from 'vue-router'

const routes: any[] = [
    {
        path: '/',
        name: 'home',
        redirect: '/guide',
    }, 
    {
        path: '/guide',
        name: 'guide',
        component: () => import('~/components/Guide.vue')
    },
    {
        path: '/hello',
        name: 'hello',
        component: () => import('~/components/HelloWorld.vue')
    }
]

export default createRouter({
    history: createWebHistory(),
    routes,
})