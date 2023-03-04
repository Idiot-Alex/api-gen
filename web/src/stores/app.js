import { createGlobalState, useStorage } from '@vueuse/core'

const COLLAPSE_STATE_KEY = 'app-collapse-state'

export const useCollapseState = createGlobalState(
  () => useStorage(COLLAPSE_STATE_KEY, false),
)

export const useToggleCollapseState = async () => {
  const collapseState = await useCollapseState()
  console.log('...', typeof !collapseState.value)
  useStorage(COLLAPSE_STATE_KEY, !collapseState.value)
}