const btn = document.querySelector("btn");

btn.addEventListener("click", () =>{
    const content = document.getElementById("#guestlistid");
    const options = { 
        margin: [10,10,10,10],
        filename: "archive.pdf", 
        html2canvas: {scale:2},
        jsPDF: { unit: "mm", format: "a4", orientation: "portrait"}
    };
    html2pdf().set(options).from(content).save();
})