import React from 'react'
import { deleteMembershipPlanAdmin } from '../api-client/api-module';

export default function MemCell({data ,setSelect,setSelectItem}) {
  return (
    <div className="entity-item">
    <div >{data.id}</div>
    <div >{data.name}</div>
    <div >{data.price}</div>
    <div >
        <button className="update-button" onClick={()=>{
            setSelectItem(data)
            setSelect('update');
        }}>Update</button>
        <button className="delete-button" onClick={async ()=>{
            await deleteMembershipPlanAdmin(data.id);
            setSelect(""+data.id);
        }}>Delete</button>
    </div>
</div>
  )
}
