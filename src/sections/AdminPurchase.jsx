import React, { useEffect, useState } from 'react'
import { getOrderList } from '../api-client/api-module';
import PurchaseCard from '../component/PurchaseCard';

export default function AdminPurchase() {

    
    const [dataList, setDataList] = useState();
    const [select, setSelect] = useState();
    const [selectItem, setSelectItem] = useState({});

    useEffect(() => {
        fetchData();
    }, [select]);



    const fetchData = async () => {
        try {
            const list = await getOrderList();
            setDataList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
  return (
    <>
    <div className="top-btn">
              
            </div>
            <div className="customer-list-container">
                
                <div className="entity-list-1">
                    {dataList && dataList.map(item => {
                        console.log(item);
                        return (
                            <PurchaseCard key={item.id} data={item}/>

                        )
                    }
                    )}
                </div>

            </div>
           
    </>
  )
}
