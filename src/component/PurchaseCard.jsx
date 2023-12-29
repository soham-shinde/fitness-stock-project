import React, { useEffect, useState } from 'react'
import { getStoreEquipmentById } from '../api-client/api-module';
import { useNavigate } from 'react-router-dom';

export default function PurchaseCard({data}) {

    const [dataItem, setDataItem] = useState();
    const navigator = useNavigate();


    useEffect(() => {
        fetchData();
    }, []);



    const fetchData = async () => {
        try {
            const raw = await getStoreEquipmentById(data.orderProduct);
            setDataItem(raw);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    return (
        <div className="purchase-item">
            <p className="purchase-date">Date: {data.orderDate.split("T")[0]}</p>
            <p className="purchase-details">Purchased: {dataItem&&dataItem.name}</p>
            <p className="purchase-price">Price: ₹ {data.totalAmount}</p>
            <button className="btn" onClick={()=>{
                navigator('/equipment-store/admin/'+data.orderProduct)
            }}>View</button>
        </div>
    )
}
