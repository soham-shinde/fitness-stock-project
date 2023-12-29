import React, { useEffect, useState } from 'react'
import CusCell from '../component/CusCell'
import { getCustomerList } from '../api-client/api-module';
import FormCustomer from '../form/FormCustomer';

export default function AdminCutomers() {

    const [dataList, setDataList] = useState();
    const [select, setSelect] = useState();
    const [selectItem, setSelectItem] = useState({});

    useEffect(() => {
        fetchData();
    }, [select]);



    const fetchData = async () => {
        try {
            const list = await getCustomerList();
            setDataList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
    return (
        <>

            <div className="customer-list-container">
                <div className='entity-heading-1'>
                    <div className="entity-heading">
                        <div >Customer ID</div>
                        <div >Name</div>
                        <div >Contact Number</div>
                        <div>Actions</div>
                    </div>
                </div>
                <div className="entity-list-1">
                    {dataList && dataList.map(item => {
                        return (
                            <CusCell key={item.id} data={item} setSelect={setSelect} setSelectItem={setSelectItem} />
                        )
                    }
                    )}


                </div>

            </div>

            {select==='update'&&<FormCustomer selectItem={selectItem} setSelect={setSelect}/>}

        </>
    )
}
