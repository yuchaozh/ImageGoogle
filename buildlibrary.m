function buildlibrary(filename)
    cd(filename);
    files=dir('*.jpg');
    m = size(files);
    histolib = [];%this is the library of all the images each element is a struct named eachimg
    eachimg.name = '';%this part contains the the name
    eachimg.colorhisto = zeros(64,64,64);%this part contains the color histogram
    eachimg.size = 0;
    for i=1:1:m(1,1)
        img = imread (files(i).name);
        eachimg.name = files(i).name;
        eachimg = histo(img,eachimg);
        histolib = cat(1,histolib,eachimg);%get the color histogram
        eachimg.name = '';
        eachimg.colorhisto = zeros(64,64,64);
        %imshow(img);
    end
    cd('..');
    save ('histolib','histolib');
    clear;
end

function eachimg = histo(img,eachimg)
    img_r = img(:,:,1);
    img_g = img(:,:,2);
    img_b = img(:,:,3);
    [m,n,k] = size(img);
    eachimg.size = 3*m*n;
    for i=1:1:m
        for j=1:1:n
            r = img_r(i,j);
            g = img_g(i,j);
            b = img_b(i,j);
            index_r = fix(double(r)/4);
            index_g = fix(double(g)/4);
            index_b = fix(double(b)/4);
            eachimg.colorhisto(index_r+1,index_g+1,index_b+1) = eachimg.colorhisto(index_r+1,index_g+1,index_b+1) + 1;
        end
    end
    eachimg.colorhisto = eachimg.colorhisto / (m*n*3);
end