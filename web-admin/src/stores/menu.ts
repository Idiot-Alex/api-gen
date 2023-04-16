import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useMenuStore = defineStore('menu', () => {
    const menuIndex = ref('')
    const changeMenuIndex = (newIndex: string) => {
        menuIndex.value = newIndex
    }
    return { menuIndex, changeMenuIndex }
})