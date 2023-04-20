import { createRouter, createWebHistory } from 'vue-router'

const routes: any[] = [
    {
        path: '/',
        name: 'home',
        redirect: '/guide',
    },
    {
        path: '/:catchAll(.*)',
        name: 'notFound',
        component: () => import('~/components/NotFound.vue')
    },
    {
        path: '/guide',
        name: 'guide',
        component: () => import('~/components/Guide.vue')
    },
    {
        path: '/api-log-list',
        name: 'api-log',
        component: () => import('~/components/ApiLogList.vue')
    },
    {
        path: '/sys-config-list',
        name: 'sys-config',
        component: () => import('~/components/SysConfigList.vue')
    },
    {
        path: '/hello',
        name: 'hello',
        component: () => import('../components/HelloWorld.vue')
    }
]

export default createRouter({
    history: createWebHistory(),
    routes,
})
