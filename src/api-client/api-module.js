
import axios from "axios";

const url = 'http://127.0.0.1:8080';

export async function verifyUser(email, password, type) {
    try {
        const formData = new FormData();
        formData.append("username", email);
        formData.append("password", password);
        // formData.append("type", "admin");
        const response = await axios.post(`${url}/api/login`, formData, { headers: { 'Content-Type': 'multipart/form-data' } });
        console.log(response);
        sessionStorage.setItem('token', response.data.token);
        sessionStorage.setItem('userId', response.data.id);
        sessionStorage.setItem('usertype', response.data.type);
        sessionStorage.setItem('login', true);
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function logoutUser() {
    try {
        const formData = new FormData();
        formData.append("token", sessionStorage.getItem('token'));
        const response = await axios.post(`${url}/api/logout`, formData, { headers: { 'Content-Type': 'multipart/form-data', Token: sessionStorage.getItem('token') } });
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('login');
        sessionStorage.removeItem('userId');
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function getOtp(email) {
    try {
        const formData = new FormData();
        formData.append("email", email);
        const response = await axios.post(`${url}/api/getOTP`, formData, { headers: { 'Content-Type': 'multipart/form-data'} });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error("Error:", error);
        return false;
    }
}



export async function getTrainersList() {
    try {
        const response = await axios.get(`${url}/api/trainer/list`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getCustomerList() {
    try {
        const response = await axios.get(`${url}/api/user/list`, { headers: { Token: sessionStorage.getItem('token') } });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function getStoreList() {
    try {
        const response = await axios.get(`${url}/api/product/list`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getMembershipPlanList() {
    try {
        const response = await axios.get(`${url}/api/plans/list`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getOrderList() {
    try {
        const response = await axios.get(`${url}/api/order/list`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getStoreEquipmentById(id) {
    try {
        const response = await axios.get(`${url}/api/product/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getUserListById(id) {
    try {
        console.log(id);
        const response = await axios.get(`${url}/api/trainer/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function getCustomerById(id) {
    try {
        const response = await axios.get(`${url}/api/user/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getMembershipPlanById(id) {
    try {
        const response = await axios.get(`${url}/api/plans/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getPlanspurchasePlanById(id) {
    try {
        const response = await axios.get(`${url}/api/planspurchase/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getTrainersById(id) {
    try {
        const response = await axios.get(`${url}/api/trainer/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getUserTaskListById(id) {
    try {
        const response = await axios.get(`${url}/api/task/list/user/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getUserOrderListById(id) {
    try {
        const response = await axios.get(`${url}/api/order/list/user/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function getUserTaskById(id) {
    try {
        const response = await axios.get(`${url}/api/task/list/user/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}






export async function addEquipment(data) {
    try {
        const response = await axios.post(`${url}/api/product/create`, data, { headers: { 'Content-Type': 'multipart/form-data', Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function addTrainer(data) {
    try {
        const response = await axios.post(`${url}/api/trainer/create`, data, { headers: { 'Content-Type': 'multipart/form-data', Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function addCustomer(data) {
    try {
        const response = await axios.post(`${url}/api/user/create`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function addPlanspurchase(data) {
    try {
        const response = await axios.post(`${url}/api/planspurchase/create`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function addOrder(data) {
    try {
        const response = await axios.post(`${url}/api/order/create`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function updateCutomer(data) {
    try {
        const response = await axios.post(`${url}/api/user/update`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function updateProduct(data) {
    try {
        const response = await axios.put(`${url}/api/product/update`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function updatePlan(data) {
    try {
        const response = await axios.put(`${url}/api/plans/update`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function addContactUs(data) {
    try {
        const response = await axios.post(`${url}/api/contactus`, data);
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}



export async function addMembershipPlan(data) {
    try {
        const response = await axios.post(`${url}/api/plans/create`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function addTaskToUser(data) {
    try {
        const response = await axios.post(`${url}/api/task/create`, data, { headers: { Token: sessionStorage.getItem('token') }, });
        return response.data;
    } catch (error) {
        console.error("Error:", error);
    }
}



export async function deleteEquipment(id) {
    try {
        await axios.delete(`${url}/api/product/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
    } catch (error) {
        console.error("Error:", error);
    }
}
export async function deleteTrainer(id) {
    try {
        await axios.delete(`${url}/api/trainer/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function deleteCustomer(id) {
    try {
        await axios.delete(`${url}/api/user/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
    } catch (error) {
        console.error("Error:", error);
    }
}

export async function deleteMembershipPlanAdmin(id) {
    try {
        await axios.delete(`${url}/api/plans/${id}`, { headers: { Token: sessionStorage.getItem('token') } });
    } catch (error) {
        console.error("Error:", error);
    }
}

// export async function verifyUser(email, password) {
//     try {
//       const formData = new FormData();
//       formData.append("email", email);
//       formData.append("password", password);
//     //   const response = await axios.post(`${url}/login`, formData,{headers: {'Content-Type': 'multipart/form-data',}});
//       return true;
//     } catch (error) {
//       console.error("Error:", error);
//     }
//   }