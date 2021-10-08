<template>
  <a-layout>
    <a-layout-content :style="{background:'#fff',padding:'24px',margin:0,minHeight:'280px'}">
      <a-row>
        <a-col :span="6">
          <a-tree
              v-if="level1.length > 0"
              :tree-data="level1"
              @select="onSelect"
              :replaceFields="{title: 'name', key: 'id', value: 'id'}"
              :defaultExpandAll="true"
          >
          </a-tree>
        </a-col>
        <a-col :span="18">
        </a-col>
      </a-row>
    </a-layout-content>


  </a-layout>


</template>

<script>


import {onMounted, ref} from "vue";
import axios from "axios";
import {Tool} from "@/util/tool";
import {message} from "ant-design-vue";
import {useRoute} from "vue-router";

export default {
  name: "doc",
  setup() {
    const docs = ref();
    const route = useRoute();
    /**
     * 一级分类树，children属性就是二级分类
     * [{
     *   id: "",
     *   name: "",
     *   children: [{
     *     id: "",
     *     name: "",
     *   }]
     * }]
     */
    const level1 = ref(); // 一级分类树，children属性就是二级分类
    level1.value = [];

    /**
     * 数据查询
     **/
    const handleQuery = () => {
      axios.get("/doc/all/" + route.query.ebookId).then((response) => {
        const data = response.data;
        if (data.success) {
          docs.value = data.content;

          level1.value = [];
          level1.value = Tool.array2Tree(docs.value, 0);
        } else {
          message.error(data.message);
        }
      });
    };

    onMounted(() => {
      handleQuery();
    });
    return {
      level1,
    }
  }
}
</script>

<style scoped>

</style>