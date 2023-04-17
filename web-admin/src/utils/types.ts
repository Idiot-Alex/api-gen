import { AxiosResponse } from 'axios'

interface MyAxiosResponse extends AxiosResponse {
  code: number,
  data: any,
  total: number
}

export { MyAxiosResponse }