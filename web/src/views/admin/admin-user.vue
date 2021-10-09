<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input v-model:value="param.loginName" placeholder="登录名">
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleQuery({num: 1, size: pagination.pageSize})">
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">
              新增
            </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="users"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >

        <template v-slot:action="{ text, record }">
          <a-space size="small">

            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除后不可恢复，确认删除?"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      title="用户表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="user" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="登录名">
        <a-input v-model:value="user.loginName"/>
      </a-form-item>
      <a-form-item label="昵称">
        <a-input v-model:value="user.name"/>
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model:value="user.password"/>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from 'axios';
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'AdminUser',
  setup() {
    const param = ref();
    param.value = {};
    const users = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const loading = ref(false);
    const columns = [
      {
        title: '登录名',
        dataIndex: 'loginName',
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '密码',
        dataIndex: 'password'
      },
      {
        title: 'Action',
        key: 'action',
        slots: {customRender: 'action'}
      }
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      axios.get("/user/list", {
        params: {
          num: params.num,
          size: params.size,
          loginName: param.value.name
        }
      }).then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          users.value = data.content.list;
          // 重置分页按钮
          pagination.value.current = params.num;
          pagination.value.total = data.content.total;
        } else {
          message.error(data.message);
        }
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      handleQuery({
        num: pagination.current,
        size: pagination.pageSize
      });
    };

// -------- 表单 ---------
    const user = ref()
    const modalVisible = ref(false);
    const modalLoading = ref(false);

    //点击对话框中的提交按钮后
    const handleModalOk = () => {
      //显示加载条
      modalLoading.value = true;
      //向后端发送post请求
      axios.post("/user/save", user.value).then((response) => {
        modalLoading.value = false;
        const data = response.data;  //data=CommonResp
        if (data.success) {
          modalVisible.value = false;
          //重新加载列表
          handleQuery({
            num: pagination.value.current,
            size: pagination.value.pageSize
          });
        } else {
          message.error(data.message);
        }
      })
    };

    /**
     * 点击编辑按钮
     */
    const edit = (record: any) => {
      //显示模态对话框
      modalVisible.value = true;
      user.value = Tool.copy(record);
    };

    /**
     * 点击新增按钮
     */
    const add = () => {
      modalVisible.value = true;
      user.value = {};
    };

    //点击删除按钮
    const handleDelete = (id: number) => {
      //向后端发送delete请求
      axios.delete("/user/delete/" + id).then((response) => {
        const data = response.data;  //data=CommonResp
        if (data.success) {
          //重新加载列表
          handleQuery({
            num: pagination.value.current,
            size: pagination.value.pageSize
          });
        }
      });
    };

    onMounted(() => {
      handleQuery({
        num: 1,
        size: pagination.value.pageSize
      });

    });

    return {
      param,
      users,
      pagination,
      columns,
      loading,
      handleTableChange,
      handleQuery,

      edit,
      add,

      user,
      modalVisible,
      modalLoading,
      handleModalOk,

      handleDelete,
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>