<template>
  <div class="container grid-lg">
    <h2>Sorveteria SU</h2>
    <div class="container-acoes">
      <SorveteNovo />
    </div>
    <table class="styled-table">
      <thead>
        <tr>
          <th>Nome</th>
          <th>Categoria</th>
          <th>Preço</th>
          <th>Desconto</th>
          <th>Quantidade</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody class="active-row">
        <tr v-for="sorvete in sorvetes" :key="sorvete.id">
          <td>{{ sorvete.nome }}</td>
          <td>{{ sorvete.categoria }}</td>
          <td>R$ {{ sorvete.preco }}</td>
          <td>R$ {{ sorvete.preco }}</td>
          <td>{{ sorvete.qnt }}</td>
          <td class="td-actions">
            <button class="bt">EDITAR</button>
            <button @click="deletarSorvete(sorvete.id)" class="bt">
              DELETAR
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import iSorvete from "@/domain/iSorvete";
import SorveteNovo from "@/views/components/SorveteNovo.vue";
import { onMounted, PropType, reactive, toRefs } from "@vue/runtime-core";
import API from "@/domain/api";

export default {
  name: "ProductList",
  components: {
    SorveteNovo,
  },
  setup() {
    const data = reactive({
      sorvetes: [] as iSorvete[],
    });

    async function deletarSorvete(id: number) {
      data.sorvetes = data.sorvetes.filter((p) => p.id != id);
      await API.deletarSorvete(id);
    }

    onMounted(async () => {
      data.sorvetes = await API.buscarSorvetes();
    });

    return {
      ...toRefs(data),
      deletarSorvete,
    };
  },
};
</script>

<style scoped>
.styled-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  min-width: 800px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table thead tr {
  background-color: #009879;
  color: #ffffff;
  text-align: center;
}
.styled-table th,
.styled-table td {
  padding: 12px 1dpx;
  padding: 30px;
}
.styled-table tr {
  border-bottom: 1px solid #dddddd;
  padding: 10px;
}
.styled-table tr:nth-of-type(even) {
  background-color: #f3f3f3;
}
.styled-table tr:last-of-type {
  border-bottom: 2px solid #009879;
}
.active-row :hover {
  color: #009879;
  cursor: pointer;
}
.td-actions {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  color: rgb(99, 96, 96);
  margin: none;
  padding: none;
}
.td-actions :hover {
  color: rgb(99, 96, 96);
}
.bt {
  display: flex;
  justify-content: center;
  width: 80px;
  padding: none;
  margin: 5px;
}
.icon {
  font-size: 18px;
}
.container-acoes {
  display: flex;
  justify-content: right;
}
#bt-delete-all {
  display: flex;
  justify-content: center;
  width: 80px;
  padding: none;
  margin: 5px;
}
</style>
