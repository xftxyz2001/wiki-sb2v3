<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
          <MailOutlined>
            <span>欢迎</span>
          </MailOutlined>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span><user-outlined/>{{ item.name }}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined/>
            <span>{{ child.name }}</span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <div class="welcome" v-show="isShowWelcome">
        <the-welcome></the-welcome>
      </div>

      <a-list v-show="!isShowWelcome" item-layout="vertical" size="large" :data-source="ebooks"
              :grid="{ gutter: 20, column: 3 }">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <!--            点赞数阅读数 -->
            <template #actions>
              <span>
                <component v-bind:is="'FileOutlined'" style="margin-right: 8px"/>
                {{ item.docCount }}
              </span>
              <span>
                <component v-bind:is="'UserOutlined'" style="margin-right: 8px"/>
                {{ item.viewCount }}
              </span>
              <span>
                <component v-bind:is="'LikeOutlined'" style="margin-right: 8px"/>
                {{ item.voteCount }}
              </span>
            </template>
            <a-list-item-meta :description="item.description">
              <!-- 标题-->
              <template #title>
                <router-link :to="'/doc?ebookId='+item.id">
                  {{ item.name }}
                </router-link>
              </template>
              <!--  封面图-->
              <template #avatar>
                <a-avatar :src="item.cover"/>
              </template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import {FileOutlined, UserOutlined, LikeOutlined,} from '@ant-design/icons-vue';
import axios from "axios";
import {Tool} from "@/util/tool";
import {message} from "ant-design-vue";
import TheWelcome from '@/components/the-welcome.vue';

export default defineComponent({
  name: 'Home',
  components: {
    FileOutlined,
    UserOutlined,
    LikeOutlined,
    TheWelcome
  },
//  通过axios调用电子书列表接口
  setup() {
    //绑定响应式变量
    const ebooks = ref();

    const actions: Record<string, string>[] = [
      {type: 'StarOutlined', text: '156'},
      {type: 'LikeOutlined', text: '156'},
      {type: 'MessageOutlined', text: '2'},
    ];

    const level1 = ref(); // 一级分类树，children属性就是二级分类
    let categorys: any;

    /**
     * 分类数据查询
     **/
    const handleQueryCategory = () => {
      axios.get("/category/all").then((response) => {
        const data = response.data;
        if (data.success) {
          categorys = data.content;
          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构：", level1.value);
        } else {
          message.error(data.message);
        }
      });
    };

    const isShowWelcome = ref(true);
    let categoryId2 = 0;


    //点击菜单栏时触发
    const handleClick = (value: any) => {
      if (value.key == 'welcome') {
        isShowWelcome.value = true;
      } else {
        categoryId2 = value.key;
        isShowWelcome.value = false;
        handleQueryEbook();
      }

    };

    const handleQueryEbook = () => {
      //发送请求
      axios.get("/ebook/list", {
        params: {
          num: 1,
          size: 1000,
          categoryId2: categoryId2
        }
      }).then((response) => {
        //响应数据的文本传递给响应式变量返回
        const data = response.data;
        ebooks.value = data.content.list;
      });
    }


    //组件加载完毕，界面渲染完毕后执行
    onMounted(() => {
      handleQueryCategory();
    });
    return {
      ebooks,
      actions,
      level1,
      handleClick,
      isShowWelcome,
    };
  },
});
</script>
<style scoped>
/*封面图片样式*/
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}
</style>