import React from 'react'
import { deleteCustomer } from '../api-client/api-module';

export default function CusCell({data ,setSelect,setSelectItem}) {
    return (
        <div className="entity-item">
            <div >{data.id}</div>
            <div >{data.name}</div>
            <div >{data.contactno}</div>
            <div >
                <button className="update-button" onClick={()=>{
            setSelectItem(data)
            setSelect('update');
        }}>Update</button>
                <button className="delete-button"onClick={async ()=>{
            await deleteCustomer(data.id);
            setSelect(data.id);
        }}>Delete</button>
            </div>
        </div>
    )
}
