import React from 'react'
import { deleteTrainer } from '../api-client/api-module';

export default function TraiCell({data ,setSelect,setSelectItem}) {
  return (
    <div className="entity-item">
    <div >{data.id}</div>
    <div ><img src={data.image} alt="" /></div>
    <div >{data.name}</div>
    <div >
        <button className="update-button"onClick={()=>{
            setSelectItem(data)
            setSelect('update');
        }}>Update</button>
        <button className="delete-button"
        onClick={async ()=>{
          await deleteTrainer(data.id);
          setSelect(' ');
      }}>Delete</button>
    </div>
</div>
  )
}
