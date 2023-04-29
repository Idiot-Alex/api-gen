import { AxiosResponse } from 'axios'

interface MyAxiosResponse extends AxiosResponse {
  code: number,
  msg: string,
  data: any,
  total: number
}

export { MyAxiosResponse }