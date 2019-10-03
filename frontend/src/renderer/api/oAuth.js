import request from '@/utils/request'

let Api = Function();
let axios = request.axiosIntercept();

Api.prototype = {
    getUser(code, fn) {
        axios.get(`/github/getUser/${code}`).then(res => {//想后端发当前用户的code，后端通过验证id secret后返回一个用户信息对象
            // if (res.data.code === 200) {
            fn(res.data)
            // }
        }).error()

    }
}
export default new Api()


