import React, { useState } from 'react'
import { addTrainer, updateCutomer } from '../api-client/api-module';
import { formatDate, validateContact, validateEmail, validatePassword } from '../api-client/validators';

export default function FormCustomer({ setSelect, selectItem, trainerData }) {

    const [id, setId] = useState(selectItem ? selectItem.id : 0);
    const [name, setName] = useState(selectItem ? selectItem.name : '');
    const [dob, setDob] = useState(selectItem ? selectItem.dob.split('T')[0] : '');
    const [gender, setGender] = useState(selectItem ? selectItem.gender : 'Male');
    const [contactNo, setContactNo] = useState(selectItem ? selectItem.contactno : '');
    const [address, setAddress] = useState(selectItem ? selectItem.address : '');
    const [weight, setWeight] = useState(selectItem ? selectItem.weight : '');
    const [height, setHeight] = useState(selectItem ? selectItem.height : '');
    const [username, setUsername] = useState(selectItem ? selectItem.username : '');
    const [password, setPassword] = useState(selectItem ? selectItem.password : '');
    const [trainer, setTrainer] = useState(selectItem ? selectItem.trainer : '');
    const [userPlansPurchase, setUserPlansPurchase] = useState(selectItem ? selectItem.userPlansPurchase : '');
    const [tasks, setTasks] = useState(selectItem ? selectItem.tasks : []);
    const [orders, setOrders] = useState(selectItem ? selectItem.orders : []);
    const [errors, setErrors] = useState({});

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newErrors = {};
        if (!name ||!dob ||!gender ||!contactNo ||!weight ||!height ||(!trainerData && (!trainer || !username || !password)) ||!address) {
            newErrors.data = 'All fields are required';
            return;
        }
        if (!validateEmail(username)) {
            newErrors.data = "Invalid username username should in email formate"
        }

        if (!validatePassword(password)) {
            newErrors.data = "Password should be minimum 6 digit"
        }
        if (!validateContact(contactNo)) {
            newErrors.data = "Invalid Number Enter 10 digit No."
        }

        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }
        const formData = {
            "id": id,
            "name": name,
            "dob": formatDate(dob),
            "gender": gender,
            "contactno": contactNo,
            "address": address,
            "weight": weight,
            "height": height,
            "username": username,
            "password": password,
            "trainer": trainer ? trainer : '',
            "userPlansPurchase": userPlansPurchase ? userPlansPurchase : 0,
            "tasks": tasks,
            "orders": orders
        }


        // Now you can use the formData object for further processing
        console.log('Form Data:', formData);
        await updateCutomer(formData).then(() => {
            handelClose();
        })
    };

    function handelClose(params) {
        setSelect('');
    }

    return (
        <div id="equipmentAddModal" className="modal">
            <div className="modal-content entity-add-form">
                <span className="close" onClick={handelClose}>&times;</span>

                <h2>{trainerData ? "Details" : "Update Customer"}</h2>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="name">Name:</label>
                    <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} required disabled={trainerData} />


                    <div className="input-group">
                        <div>
                            <label htmlFor="dob">Date of Birth:</label>
                            <input type="date" id="dob" value={dob} onChange={(e) => setDob(e.target.value)} required disabled={trainerData} />

                        </div>
                        <div>
                            <label htmlFor="gender">Gender:</label>
                            <select id="gender" value={gender} onChange={(e) => setGender(e.target.value)} required disabled={trainerData}>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>

                        <div>
                            <label htmlFor="contactNo">Contact Number:</label>
                            <input type="tel" id="contactNo" value={contactNo} onChange={(e) => setContactNo(e.target.value)} required disabled={trainerData} />

                        </div>
                        <div>
                            <label htmlFor="weight">Weight:</label>
                            <input type="number" id="weight" value={weight} required onChange={(e) => setWeight(e.target.value)} disabled={trainerData} />

                        </div>
                        <div>
                            <label htmlFor="height">Height:</label>
                            <input type="number" id="height" value={height} required onChange={(e) => setHeight(e.target.value)} disabled={trainerData} />
                        </div>
                        <div>
                            <label htmlFor="trainer">Trainer:</label>
                            <input type="number" id="trainer" value={trainer} required onChange={(e) => setTrainer(e.target.value)} disabled={trainerData} />
                        </div>
                        <div>

                            <label htmlFor="username">Username:</label>
                            <input type="text" id="username" value={username} onChange={(e) => setUsername(e.target.value)} required disabled={trainerData} />

                        </div>
                        <div>
                            <label htmlFor="password">Password:</label>
                            <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required disabled={trainerData} />
                        </div>
                    </div>


                    <label htmlFor="address">Address:</label>
                    <input type="text" id="address" value={address} required onChange={(e) => setAddress(e.target.value)} disabled={trainerData} />
                    {errors.data && <div className="error">{errors.data}</div>}


                    {!trainerData && <div className='btn-container-1'>
                        <button type="submit" className="add-button btn" disabled={trainerData}>Update</button>
                    </div>}
                </form>

            </div>
        </div>
    )
}
