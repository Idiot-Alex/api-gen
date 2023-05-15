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
        path: '/api-doc',
        name: 'api-doc',
        component: () => import('~/components/ApiDocument.vue')
    },
    {
        path: '/dashboard',
        name: 'dashboard',
        component: () => import('~/components/Dashboard.vue')
    },
    {
        path: '/sys-config-list',
        name: 'sys-config',
        component: () => import('~/components/SysConfigList.vue')
    },
]

export default createRouter({
    history: createWebHistory(),
    routes,
})
