import React from 'react'
import { deleteEquipment } from '../api-client/api-module';

export default function EqCell({data ,setSelect,setSelectItem}) {
  return (
    <div className="entity-item">
    <div >{data.id}</div>
    <div >{data.name}</div>
    <div ><img src={data.image1} alt="" /></div>
    <div >
        <button className="update-button" onClick={()=>{
            setSelectItem(data)
            setSelect('update');
        }}>Update</button>
        <button className="delete-button" onClick={async ()=>{
            await deleteEquipment(data.id);
            setSelect(""+data.id);
        }}>Delete</button>
    </div>
</div>
  )
}
