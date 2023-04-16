import { defineAsyncComponent } from 'vue'
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
        component: () => defineAsyncComponent(() => import('~/components/Guide.vue'))
    },
    {
        path: '/hello',
        name: 'hello',
        component: () => defineAsyncComponent(() => import('~/components/HelloWorld.vue'))
    }
]

export default createRouter({
    history: createWebHistory(),
    routes,
})
