import axios from "axios";
import iSorvete from "./iSorvete";

const api = axios.create({
    baseURL: "http://localhost:8090"
})

const buscarSorvetes = async (): Promise<iSorvete[]> => {
    const response = await api.get("/sorvetes");
    return response.data;
};

const salvarSorvete = async (product: iSorvete): Promise<iSorvete> => {
    const response = await api.post("/sorvetes", product)
    return response.data;
}

async function deletarSorvete(id: number){
    await api.delete(`/sorvetes/${id}`)
}

export default {
    buscarSorvetes,
    salvarSorvete,
    deletarSorvete
}