import {
  createStore
} from 'vuex'


export default createStore({
  state: {
      identity:'系统管理员',
      tagsList: [],
      collapse: true,
      systemTwoPort: 5502, // 必须得是异步加载 - 可以使用vuex-action，也可以将异步过程放在外部执行
  

      // 通过vuex传递map的layerID,不如父子组件传参方便,暂时不用
      mapMainLayerID: []
  },
  getters: {
      sidebarGetter(state) {
          return state.sidebar;
      }
  },
  mutations: {
      initIdentity(state,key){
        state.identity = key
      },

      delTagsItem(state, data) {
          state.tagsList.splice(data.index, 1);
      },
      setTagsItem(state, data) {
          state.tagsList.push(data)
      },
      clearTags(state) {
          state.tagsList = []
      },
      closeTagsOther(state, data) {
          state.tagsList = data;
      },
      closeCurrentTag(state, data) {
          for (let i = 0, len = state.tagsList.length; i < len; i++) {
              const item = state.tagsList[i];
              if (item.path === data.$route.fullPath) {
                  if (i < len - 1) {
                      data.$router.push(state.tagsList[i + 1].path);
                  } else if (i > 0) {
                      data.$router.push(state.tagsList[i - 1].path);
                  } else {
                      data.$router.push("/");
                  }
                  state.tagsListsplice(i, 1);
                  break;
              }
          }
      },
      // 侧边栏折叠
      hadndleCollapse(state, data) {
          state.collapse = data;
      }
  },
  actions: {},
  modules: {}
})