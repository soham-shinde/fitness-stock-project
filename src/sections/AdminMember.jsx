import React, { useEffect, useState } from 'react'
import { getMembershipPlanList } from '../api-client/api-module';
import FormMemberShipAdd from '../form/FormMemberShipAdd';
import MemCell from '../component/MemCell';

export default function AdminMember() {

    const [dataList, setDataList] = useState();
    const [select, setSelect] = useState();
    const [selectItem, setSelectItem] = useState({});

    useEffect(() => {
        fetchData();
    }, [select]);



    const fetchData = async () => {
        try {
            const list = await getMembershipPlanList();
            setDataList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
  return (
    <>
    <div className="top-btn">
                <button className="add-btn" id="add-user" onClick={()=>{setSelect('add')}}>
                    Add Plan
                </button>
            </div>
            <div className="customer-list-container">
                <div className='entity-heading-1'>
                    <div className="entity-heading">
                        <div >Plan ID</div>
                        <div >Name</div>
                        <div >Price</div>
                        <div>Actions</div>
                    </div>
                </div>
                <div className="entity-list-1">
                    {dataList && dataList.map(item => {
                        console.log(item);
                        return (
                            <MemCell key={item.id} data={item} setSelect={setSelect} setSelectItem={setSelectItem}/>
                        )
                    }
                    )}
                </div>

            </div>
            {select==='add'&&<FormMemberShipAdd setSelect={setSelect}/>}
            {select==='update'&&<FormMemberShipAdd setSelect={setSelect} selectItem={selectItem}/>}
        
    </>
  )
}
