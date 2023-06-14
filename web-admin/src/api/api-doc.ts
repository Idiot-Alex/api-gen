import axios from './config'
import { MyAxiosResponse } from '~/utils/types'

/**
 * create a new doc
 * @param params 
 * @returns 
 */
export function create(params: Object): Promise<MyAxiosResponse> {
  return axios.post('/w/api-doc/create', {
    ...params
  }, {
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
