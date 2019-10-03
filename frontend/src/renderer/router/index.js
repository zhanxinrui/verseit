import Vue from 'vue'
import Router from 'vue-router'
import Index from 'container/index'
import LoginIndex from "../container/login/Index"
import Login from "../container/login/login"
import Register from "../container/login/Register"
import Blog from "../container/blog/Blog"
import EditBlog from "../container/blog/EditBlog"
import User from "../container/user/User"
import Setting from "../container/Setting/setting"
import Life from "../container/life/Life"
import Book from "../container/book/Book"
import notification from "../container/Notification/notification";
import personal from "../container/Personal/personal";
import compose from "../container/Compose/compose";
import pubPoem from "../container/Square/pubPoem";
import userPoem from "../container/Square/userPoem";
import concernPoem from "../container/Square/concernPoem";
import collect from "../container/Personal/collect";
import concernUser from "../container/Personal/concernUser";
import myCompose from "../container/Personal/myCompose";
import personalInfo from "../container/Setting/personalInfo";
import privacy from "../container/Setting/privacy";
import application from "../container/Setting/application";
import layout from "../container/Setting/layout";

Vue.use(Router)

let route = [
    {
        path: '/',
        name: 'index',
        component: Index,
        children: [ {
            path: 'pubPoem',
            name: 'pubPoem',
            component: pubPoem
        },{
            path: 'userPoem',
            name: 'userPoem',
            component: userPoem
        },{
            path: 'concernPoem',
            name: 'concernPoem',
            component: concernPoem
        }
            ]
    },
    {
        path: '/login',
        component: LoginIndex,
        children: [{
            path: '',
            redirect: 'index'
        }, {
            path: 'index',
            name: 'login',
            component: Login
        }, {
            path: 'register',
            name: 'register',
            component: Register
        }]

    },
    {
        path: '/blog/:id',
        name: 'blog',
        component: Blog,

    },
    {
        path: '/life',
        name: 'life',
        component: Life,

    },
    {
        path: '/editor/post/new',
        name: '发文章',
        component: EditBlog,

    },
    {
        path: '/editor/edit/:id',
        name: '编辑文章',
        component: EditBlog,

    },

    {
        path: '/book',
        name: 'book',
        component: Book
    },
    {
        path: '/user/:userName',
        name: '用户页',
        component: User
    },
    {
        path: '/setting',
        name: 'Setting',
        component: Setting,
        children: [ {
            path: 'personalInfo',
            name: 'personalInfo',
            component: personalInfo,
        }, {
            path: 'layout',
            name: 'layout',
            component: layout,
        }, {
            path: 'application',
            name: 'application',
            component: application,
        }, {
            path: 'privacy',
            name: 'privacy',
            component: privacy
        }]
    },
    {
        path: '/notification',
        name: 'notification',
        component: notification
    },
    {
        path: '/personal',
        name: 'personal',
        component: personal,
        children: [ {
            path: 'collect',
            name: 'collect',
            component: collect,
        }, {
            path: 'concernUser',
            name: 'concernUser',
            component: concernUser,
        }, {
            path: 'myCompose',
            name: 'myCompose',
            component: myCompose,
        }]
    },
    {
        path: '/compose',
        name: 'compose',
        component: compose
    }
]

// 加载路由模块


export default new Router({
    routes: route,

})
