import axios from "axios";
import iSorvete from "./iSorvete";

const api = axios.create({
    baseURL: "http://localhost:8090"
})

const getProducts = async (): Promise<iSorvete[]> => {
    const response = await api.get("/sorvetes");
    return response.data;
};

const save = async (product: iSorvete): Promise<iSorvete> => {
    const response = await api.post("/sorvetes", product)
    return response.data;
}

async function deleteProduct(id: number){
    await api.delete(`/sorvetes/${id}`)
}

export default {
    deleteProduct,
    getProducts,
    save,
}