import React, { useEffect, useState } from 'react'
import PurchaseCard from '../component/PurchaseCard'
import { getUserOrderListById } from '../api-client/api-module';

export default function UserStoreHis({userId}) {
    const [dataList, setDataList] = useState();

    useEffect(() => {
        fetchData();
    }, []);



    const fetchData = async () => {
        try {
            const raw = await getUserOrderListById(userId);
            setDataList(raw);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };
    return (
        <>
            <h2>Purchase History</h2>
            <div className="purchase-history-list overflow-scroll-parent">
                <div className='overflow-scroll' style={{height:'60vh'}}>
            {dataList && dataList.map(item => {
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
