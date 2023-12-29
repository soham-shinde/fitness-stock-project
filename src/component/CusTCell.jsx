import React from 'react'

export default function CusTCell({data,setOption,setSelect,setSelectItem}) {
    return (

        <div className="customer-item">
            <h3 className="customer-name">{data.name}</h3>
            <p className="customer-info">Email: {data.username}</p>
            <p className="contact-info">Contact: {data.contactno}</p>

            <div>
                <button className="view-customer-details-button" onClick={()=>{
                    setOption('view')
                    setSelectItem(data);
                }}>
                    View Details
                </button>
                <button className="view-customer-details-button" onClick={()=>{
                    setOption('assign');
                    setSelectItem(data);

                }}>
                    Assign Task
                </button>
                <button className="view-customer-details-button" onClick={()=>{
                    setOption('task');
                    setSelectItem(data);

                }}>
                    View Task
                </button>
            </div>
        </div>
    )
}
