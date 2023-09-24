import { type RouteRecordRaw, createRouter, createWebHashHistory, createWebHistory } from "vue-router";

const Layout = () => import("@/layout/index.vue");

/** 常驻路由 */
export const constantRoutes: RouteRecordRaw[] = [
  {
    path: "/redirect",
    component: Layout,
    meta: {
      hidden: true
    },
    children: [
      {
        path: "/redirect/:path(.*)",
        component: () => import("@/views/redirect/index.vue")
      }
    ]
  },
  {
    path: "/403",
    component: () => import("@/views/error-page/403.vue"),
    meta: {
      hidden: true
    }
  },
  {
    path: "/404",
    component: () => import("@/views/error-page/404.vue"),
    meta: {
      hidden: true
    },
    alias: "/:pathMatch(.*)*"
  },
  {
    path: "/login",
    component: () => import("@/views/login/index.vue"),
    meta: {
      hidden: true
    }
  },
  // {
  //   path: "/",
  //   component: Layout,
  //   redirect: "/dashboard",
  //   children: [
  //     {
  //       path: "dashboard",
  //       component: () => import("@/views/dashboard/index.vue"),
  //       name: "Dashboard",
  //       meta: {
  //         title: "首页",
  //         svgIcon: "dashboard",
  //         affix: true
  //       }
  //     }
  //   ]
  // },
  // {
  //   path: "/",
  //   component: Layout,
  //   redirect: "/list",
  //   children: [
  //     {
  //       path: "",
  //       component: () => import("@/views/table/element-plus/index.vue"),
  //       name: "订单",
  //       meta: {
  //         title: "订单列表",
  //         keepAlive: true,
  //         svgIcon: "dingdan",
  //         affix: true
  //       }
  //     }
  //   ]
  // },

  {
    path: "/",
    component: Layout,
    redirect: "/products",
    name: "items",
    meta: {
      title: "商品管理",
      elIcon: "Grid"
    },
    children: [
      {
        path: "products",
        component: () => import("@/views/items/products/index.vue"),
        name: "homePageProduct",
        meta: {
          title: "商品管理",
          keepAlive: true
        }
      },
      {
        path: "category",
        component: () => import("@/views/items/category/index.vue"),
        name: "category",
        meta: {
          title: "商品分类管理",
          keepAlive: true
        }
      }
    ]
  },
  {
    path: "/user",
    component: Layout,
    redirect: "/user",
    name: "user",
    meta: {
      title: "用户管理",
      elIcon: "User"
    },
    children: [
      {
        path: "users",
        component: () => import("@/views/user/users/index.vue"),
        name: "users",
        meta: {
          title: "普通用户管理",
          keepAlive: true
        }
      },
      {
        path: "deliver",
        component: () => import("@/views/user/deliver/index.vue"),
        name: "deliver",
        meta: {
          title: "配送员管理",
          keepAlive: true
        }
      }
    ]
  }
];

/**
 * 动态路由
 * 用来放置有权限 (Roles 属性) 的路由
 * 必须带有 Name 属性
 */
export const asyncRoutes: RouteRecordRaw[] = [
  // {
  //   path: "/permission",
  //   component: Layout,
  //   redirect: "/permission/page",
  //   name: "Permission",
  //   meta: {
  //     title: "权限管理",
  //     svgIcon: "lock",
  //     roles: ["admin", "editor"], // 可以在根路由中设置角色
  //     alwaysShow: true // 将始终显示根菜单
  //   },
  //   children: [
  //     {
  //       path: "page",
  //       component: () => import("@/views/permission/page.vue"),
  //       name: "PagePermission",
  //       meta: {
  //         title: "页面权限",
  //         roles: ["admin"] // 或者在子导航中设置角色
  //       }
  //     },
  //     {
  //       path: "directive",
  //       component: () => import("@/views/permission/directive.vue"),
  //       name: "DirectivePermission",
  //       meta: {
  //         title: "指令权限" // 如果未设置角色，则表示：该页面不需要权限，但会继承根路由的角色
  //       }
  //     }
  //   ]
  // },
  {
    path: "/:pathMatch(.*)*", // Must put the 'ErrorPage' route at the end, 必须将 'ErrorPage' 路由放在最后
    redirect: "/404",
    name: "ErrorPage",
    meta: {
      hidden: true
    }
  }
];

const router = createRouter({
  history:
    import.meta.env.VITE_ROUTER_HISTORY === "hash"
      ? createWebHashHistory(import.meta.env.VITE_PUBLIC_PATH)
      : createWebHistory(import.meta.env.VITE_PUBLIC_PATH),
  routes: constantRoutes
});

/** 重置路由 */
export function resetRouter() {
  // 注意：所有动态路由路由必须带有 Name 属性，否则可能会不能完全重置干净
  try {
    router.getRoutes().forEach((route) => {
      const { name, meta } = route;
      if (name && meta.roles?.length) {
        router.hasRoute(name) && router.removeRoute(name);
      }
    });
  } catch (error) {
    // 强制刷新浏览器也行，只是交互体验不是很好
    window.location.reload();
  }
}

export default router;
