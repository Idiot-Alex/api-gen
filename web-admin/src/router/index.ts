import { defineAsyncComponent } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

/**
 * 定义异步组件
 * @param {string} path 组件路径
 * @param {number} delay 延迟时间，单位毫秒
 * @returns {Object} 异步组件对象
 */
// const getAsyncComponent = (path: string, delay: number = 1000): object => {
//   return defineAsyncComponent({
//     loader: () => {
//       return new Promise((resolve) => {
//         setTimeout(() => {
//           /* @vite-ignore */
//           resolve(import(path))
//         }, delay)
//       })
//     }
//   })
// }
// const HelloWorld = getAsyncComponent('../components/HelloWorld.vue')

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
        component: () => import('../components/HelloWorld.vue')
    }
]

export default createRouter({
    history: createWebHistory(),
    routes,
})
