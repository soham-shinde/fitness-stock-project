import React, { useEffect, useState } from 'react'
import NavBar from '../component/NavBar';
import CusTCell from '../component/CusTCell';
import { getCustomerList, getUserListById } from '../api-client/api-module';
import { useParams } from 'react-router-dom';
import FormTaskAssign from '../form/FormTaskAssign';
import FormTaskComplition from '../form/FormTaskComplition';
import FormCustomer from '../form/FormCustomer';

export default function TrainerDashboard() {
    const [option, setOption] = useState('customers');
    const {trainerId} = useParams()
    const [dataList, setDataList] = useState();
    const [select, setSelect] = useState();
    const [selectItem, setSelectItem] = useState();
    const [userData, setUserData] = useState();
    const [trainerData, setTrainerData] = useState({});

    useEffect(() => {
        fetchData();
    }, [select]);



    const fetchData = async () => {
        try {
            console.log(trainerId);
            const d = await getUserListById(trainerId);
            const list = await getCustomerList(trainerId);
            setDataList(list.filter(customer => d.users.includes(customer.id)))
            console.log(list);
            setTrainerData(d);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    return (
        <>
            <NavBar user="trainer"/>
            <div className="body-container">
                <div className="a-container">
                    <div className="siderbar-menu">
                        <ul>
                            <li>
                                <div className={`menu-option ${option === 'customers' ? 'active-menu' : ''}`} onClick={() => setOption('customers')}>
                                    Customers
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                           
                        </ul>
                    </div>
                    <div className="main-container" id="mainContainer">
                        <section className="assigned-customers-section ">
                            <h2>Assigned Customers</h2>
                            <div className="customer-list overflow-scroll">
                            {dataList && dataList.map(item => {
                      
                        return (
                            <CusTCell key={item.id} data={item} setOption={setOption}  setSelectItem={setUserData}/>
                        )
                    }
                    )}
                            </div>
                        </section>
                    </div>
                </div>=
                {option==='assign'&&<FormTaskAssign trainerData={trainerData} setSelect={setOption} userDate={userData}/>}
                {option==='task'&&<FormTaskComplition trainerData={trainerData} setSelect={setOption} userDate={userData}/>}
                {option==='view'&&<FormCustomer trainerData={trainerData} selectItem={userData} setSelect={setOption} userDate={userData}/>}
            </div>
            <footer>
                <div className="divider-y"></div>
                <p>&copy; 2023 Fitsta.com All rights reserved.</p>

            </footer>
        </>
    )
}
