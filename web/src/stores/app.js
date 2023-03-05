import { createGlobalState, useStorage } from '@vueuse/core'

const COLLAPSE_STATE_KEY = 'app-collapse-state'

export const useCollapseState = () => createGlobalState(
  () => useStorage(COLLAPSE_STATE_KEY, false)
)

export const useToggleCollapseState = (collapseState) => {
  console.log('useToggleCollapseState...', !collapseState.value)
  // createGlobalState(
  //   () => useStorage(COLLAPSE_STATE_KEY,  !collapseState.value),
  // )
  useCollapseState.value = !collapseState.value
  // const res = useStorage(COLLAPSE_STATE_KEY,  !collapseState.value)

  console.log('useToggleCollapseState...', useCollapseState.value)
}