import React, { useEffect, useState } from 'react'
import TraiCell from '../component/TraiCell'
import { getTrainersList } from '../api-client/api-module';
import FormTrainer from '../form/FormTrainer';

export default function AdminTrainers() {
    const [dataList, setDataList] = useState();
    const [select, setSelect] = useState();
    const [selectItem, setSelectItem] = useState({});

    useEffect(() => {
        fetchData();
    }, [select]);



    const fetchData = async () => {
        try {
            const list = await getTrainersList();
            setDataList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
  return (

    <>
            <div className="top-btn">
                <button className="add-btn" id="add-user" onClick={()=>{setSelect('add')}}>
                    Add Trainer
                </button>
            </div>
            <div className="customer-list-container">
                <div className='entity-heading-1'>
                    <div className="entity-heading">
                        <div >Trainer ID</div>
                        <div >Image</div>
                        <div >Name</div>
                        <div>Actions</div>
                    </div>
                </div>
                <div className="entity-list-1">
                {dataList && dataList.map(item => {
                        return (
                            <TraiCell key={item.id} data={item} setSelect={setSelect} setSelectItem={setSelectItem}/>
                        )
                    }
                    )}
                

                </div>

            </div>
            {select==='add'&&<FormTrainer setSelect={setSelect}/>}
            {select==='update'&&<FormTrainer setSelect={setSelect} selectItem={selectItem}/>}
       
        </>
 
  )
}
