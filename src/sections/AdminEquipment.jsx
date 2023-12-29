import React, { useEffect, useState } from 'react'
import EqCell from '../component/EqCell'
import { getStoreList } from '../api-client/api-module';
import FormEquipment from '../form/FormEquipment';

export default function AdminEquipment() {
    const [storeList, setStoreList] = useState();
    const [select, setSelect] = useState();
    const [selectItem, setSelectItem] = useState({})
   

    useEffect(() => {
        fetchData();
    }, [select]);



    const fetchData = async () => {
        try {
            const list = await getStoreList();
            setStoreList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    return (
        <>
            <div className="top-btn">
                <button className="add-btn" id="add-user" onClick={()=>{setSelect('add')}}>
                    Add Equipment
                </button>
            </div>
            <div className="customer-list-container">
                <div className='entity-heading-1'>
                    <div className="entity-heading">
                        <div >Customer ID</div>
                        <div >Name</div>
                        <div >Image</div>
                        <div>Actions</div>
                    </div>
                </div>
                <div className="entity-list-1">
                    {storeList && storeList.map(item => {
                        return (
                            <EqCell key={item.id} data={item} setSelect={setSelect} setSelectItem={setSelectItem}/>
                        )
                    }
                    )}
                </div>

            </div>
            {select==='add'&&<FormEquipment setSelect={setSelect}/>}
            {select==='update'&&<FormEquipment setSelect={setSelect} selectItem={selectItem}/>}
        </>
    )
}
